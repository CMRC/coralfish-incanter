(defproject example "1.0.0-SNAPSHOT"
  :description ""
  :dependencies [[org.clojure/clojure "1.2.0"]
		 [org.clojure/clojure-contrib "1.2.0"]
		 [compojure "0.5.2"]
		 [ring/ring-jetty-adapter "0.3.1"]
		 [hiccup "0.3.0"]
		 [incanter "1.2.3"]]
  :native-dependencies [[jriengine "0.8.4"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [native-deps "1.0.5"]]
  :main hello)