/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('EmailTemplatesService',
    function ($http) {
        var url = "api/private/emailTemplates/";

        this.getTemplates = function(){
            return $http.get(url+"all");
        };

        this.save = function(template) {
            return $http.post(url,template);l
        }




    }
)
;