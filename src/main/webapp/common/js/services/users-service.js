/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('UsersService',
    function ($http) {
        var url = "api/private/user/";

        this.save = function(user){
            return $http.put(url, user)
        };

        this.getCurrentUser = function(){
            return $http.get(url + 'getCurrent');
        };

        this.find = function(id){
            return $http.get(url + id);
        };

        this.getAllUsers = function(){
            return $http.get(url + 'getAll');
        };

        this.changePassword = function(pass1, userId){
            return $http.post(url + 'changePassword', {userId: userId, password:pass1});
        };

        this.getAllConfRegistered = function(eventId){
            return $http.get(url + 'confRegistered', {params: { eventId : eventId}});
        }
    }
)
;