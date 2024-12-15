(ns de-jong-ifs-simple.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def alpha 0.97)
(def beta -1.9)
(def gamma 1.38)
(def delta -1.5)

(defn de-jong-generate [[x y]]
  [(- (q/sin (* alpha y)) (q/cos (* beta x)))
   (- (q/sin (* gamma x)) (q/cos (* delta y)))])

(def de-jong-sequence
  (let [x0 (rand)
        y0 (rand)]
    (iterate de-jong-generate [x0 y0])))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb 360 100 100)
  (q/background 45 11 96)
  (q/stroke 217 32 21)
  {:points (take 500000 de-jong-sequence)})

(defn update-state [state]
  state)

(defn draw-state [state]
    (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
                        (let [scale 150]
                          (doseq [[x y] (:points state)]
                            (q/point (* scale x) (* scale y))))))


(q/defsketch de-jong-ifs-simple
  :title "De Jong IFS simple"
  :size [800 800]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
