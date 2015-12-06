/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('LogService',
    function ($http) {
        var url = "api/private/logs/";


        this.getAll = function(){
            return $http.get(url + 'getAll');
        };


    }
)
;