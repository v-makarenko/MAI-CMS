/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('EmailService',
    function ($http) {
        var url = "api/private/email/";

        this.distribute = function(){
            return $http.post(url + 'distribute');
        };


    }
)
;