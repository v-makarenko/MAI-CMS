/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MessagesController', ['$scope', '$rootScope', '$interval', 'UsersService', 'MessagesService', 'FileUploader',
    function ($scope, $rootScope, $interval, UsersService, MessagesService, FileUploader) {
        $scope.currentMessage = {};
        $scope.setFrom  = function(){
            $scope.isAdmin = $rootScope.currentUser.userType == 'ADMIN';
            if($scope.isAdmin) {
                $scope.currentMessage.fromId = '00000000-0000-0000-0000-000000000002';
            } else{
                $scope.currentMessage.fromId = $scope.currentUser.id;
            }
        };

        if($rootScope.currentUser){
            $scope.setFrom();
        }

        $rootScope.$on('user.loaded', $scope.setFrom);


        $scope.setCurrentUser = function(id){
            $scope.currentChatUser = _.find($scope.userList, {id:id});
            $scope.currentMessage.toId = $scope.currentChatUser.id;
            $scope.loadMessages();
        };

        $scope.getUsers = function () {
            UsersService.getAllUsers().success(function (data) {
                $scope.userList = data.data;
                if(!$scope.isAdmin){
                    $scope.setCurrentUser('00000000-0000-0000-0000-000000000002');
                }

            });
        };

        $scope.loadMessages = function () {
            var method = $scope.isAdmin ? MessagesService.getMessagesForAdmin : MessagesService.getMessagesForUser;
            method($scope.currentChatUser.id).success(function (data) {
                if (data.status === 'OK') {
                    $scope.messages = data.data;
                }
            });

        };

        $scope.send = function(){
            MessagesService.send($scope.currentMessage).success(function(){
                $scope.currentMessage = {};
                $scope.loadMessages();
                if($scope.isAdmin) {
                    $scope.currentMessage.fromId = '00000000-0000-0000-0000-000000000002';
                } else{
                    $scope.currentMessage.fromId = $scope.currentUser.id;
                }
                $scope.currentMessage.toId = $scope.isAdmin ? '00000000-0000-0000-0000-000000000002' : $scope.currentChatUser.id;
            });
        };

        $scope.addFile = function(isPhoto){

        };

        $interval(function(){
            $scope.loadMessages();
        }, 2000);

        $scope.getUsers();


        $scope.uploader = new FileUploader({
            url: 'api/private/files',
            autoUpload: true,
            removeAfterUpload : true,
            onSuccessItem : function(item, data){
                $scope.currentMessage.fileId = data.data;
            }
        });

    }
])
;

