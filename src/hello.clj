(ns hello-world
  (:use compojure.core, ring.adapter.jetty, incanter.core, incanter.stats, incanter.charts ,hiccup.core)
  (:require [compojure.route :as route])
  (:import (java.io ByteArrayOutputStream
                    ByteArrayInputStream)))


;; Pass a map as the first argument to be 
;; set as attributes of the element
(defn html-doc 
  [title & body] 
  (html 
       [:div 
	[:h2 
	 [:a {:href "/"} 
          "Generate a normal sample"]]]
       body)) 

(defn gen-samp-hist-png 
  [size-str mean-str sd-str] 
    (let [size (if (nil? size-str) 
                 1000 
                 (Integer/parseInt size-str))
          m (if (nil? mean-str) 
              0 
              (Double/parseDouble mean-str))
          s (if (nil? sd-str) 
              1 
              (Double/parseDouble sd-str))
          samp (sample-normal size 
                              :mean m 
                              :sd s)
          chart (histogram 
                  samp
                  :title "Normal Sample"
                  :x-label (str "sample-size = " size 
                                ", mean = " m 
                                ", sd = " s))
          out-stream (ByteArrayOutputStream.)
          in-stream (do
                      (save chart out-stream)
                      (ByteArrayInputStream. 
		       (.toByteArray out-stream)))]
      
      {:status 200
       :headers {"Content-Type" "image/png"}
       :body in-stream}))


 ;; define routes
(defroutes webservice
  (GET "/sample-normal" {params :params} 
    (gen-samp-hist-png (params :size) 
                       (params :mean) 
                       (params :sd))))

(run-jetty webservice {:port 8080})