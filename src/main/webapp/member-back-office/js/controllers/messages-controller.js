/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MessagesController', ['$scope', 'MessagesService', 'UserService',
    function ($scope, MessagesService, UserService) {
        $scope.newMsg = {};
        $scope.sendMsg = function() {
            MessagesService.sendMsg($scope.newMsg)
                .success(function(data){
                    if(data.status=='OK'){
                        $scope.newMsg = {};
                        $scope.getIncoming();
                    }
                });
        };

        $scope.getIncoming = function(){
            MessagesService.getIncoming().success(function(data){
                $scope.incomingMsgList = data.data;
            });
        };

        $scope.getUsers = function(){
            UserService.getAllUsers().success(function(data){
                $scope.users = data.data;
            });
        };

        $scope.setState = function(str){
            $scope.state = str;
        };

        $scope.getUsers();
        $scope.getIncoming();
        $scope.setState('INCOMING');

    }
]);

