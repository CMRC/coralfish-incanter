(defproject example "1.0.0-SNAPSHOT"
  :description ""
  :dependencies [[org.clojure/clojure "1.3.0-alpha6"]
		 [org.clojure.contrib/def "1.3.0-SNAPSHOT"]
		 [org.clojure.contrib/core "1.3.0-SNAPSHOT"]
		 [ring/ring-jetty-adapter "0.3.7"]
		 [compojure "0.6.2" :exclusions 
		  [org.clojure/clojure
		   org.clojure/clojure-contrib]]]
  :main hello)