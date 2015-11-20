/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('CoauthorsService',
    function ($http) {
        var url = "api/private/coauthors/";

        this.delete = function (id) {
            return $http.delete(url + '?id='+id);
        }

    }
)
;