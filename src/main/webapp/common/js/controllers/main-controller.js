/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MainController', ['$scope','$rootScope','$location', 'AuthService',
    function ($scope, $rootScope, $location, AuthService) {
        $rootScope.$on('user.loaded', function(){
            $scope.currentUser = $rootScope.currentUser;
        });

        $scope.currentUser = $rootScope.currentUser;

        $scope.toHome = function(){
            $location.path('/home');
        };
        $scope.toInfo = function(){
            $location.path('/pinfo');
        };
        $scope.toSettings = function(){
            $location.path('/psettings');
        };
        $scope.toMessages = function(){
            $location.path('/messages');
        };
        $scope.toPublications = function(){
            $location.path('/publications');
        };
        $scope.toEvents = function(){
            $location.path('/events');
        };
        $scope.toDistrib = function(){
            $location.path('/distribMail');
        };
        $scope.toMailTemplates = function(){
            $location.path('/emailTemplates');
        };

        $scope.exit = function(){
            AuthService.logout().success(function(){
                window.location.href = 'index.html';
            });
        };

        $scope.helpOn = function(){

        };
    }
]);

