/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('MessagesService',
    function ($http) {
        var url = "api/private/message/";

        this.getIncoming = function(){
            return $http.get(url + 'incoming');
        };

        this.sendMsg = function(msg){
            return $http.post(url + 'send', msg);
        };

    }
)
;