/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('WPService',
    function ($http) {
        var url = "api/private/wplaces/";

        this.save = function(wplace){
            return $http.put(url, wplace)
        };

        this.find = function(id){
            return $http.get(url + id);
        };

        this.getAll = function(){
            return $http.get(url + 'getAll');
        };

    }
)
;