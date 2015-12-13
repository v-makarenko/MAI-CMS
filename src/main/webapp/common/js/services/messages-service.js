/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('MessagesService',
    function ($http) {
        var url = "api/private/messages/";

        this.getUserList = function(){
            return $http.get(url + 'userList');
        };

        this.getMessagesForAdmin = function(fromId){
            return $http.get(url + 'incAdmin?fromUserId=' + fromId);
        };

        this.getMessagesForUser = function(fromId){
            return $http.get(url + 'incUser?fromUserId=' + fromId);
        };

        this.send = function(message){
            return $http.post(url + 'send', message);
        };

    }
)
;