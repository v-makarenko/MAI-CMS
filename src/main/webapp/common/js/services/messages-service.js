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
            return $http.get(url + 'incAdmin');
        };

        this.getMessagesForUser = function(fromId, toId){
            return $http.get(url + 'incUser');
        };

        this.send = function(fromId, toId){
            return $http.get(url + 'incUser');
        };

    }
)
;