package handlers;

import models.falcon.Coordinates

object HeuristicCalculation {
    fun getHaversineDistance(src: Coordinates, dest: Coordinates): Double {

        val heuristics: Double

        val R = 6371 // Radius of the earth


        val latDistance = Math.toRadians(dest.latitude - src.latitude)
        val lonDistance = Math.toRadians(dest.longitude - src.longitude)
        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + (Math.cos(Math.toRadians(src.latitude)) * Math.cos(Math.toRadians(dest.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2))
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        //        double distance = R * c * 1000 ; // convert to meters
        //        double height = 10 - 12; //elevation1 - elevation2
        //        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        //        heuuristics = Math.sqrt(distance);

        heuristics = R * c

        return heuristics
    }
}
