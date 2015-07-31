/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MainController', ['$scope','$rootScope','$location', 'AuthService',
    function ($scope, $rootScope, $location, AuthService) {
        $scope.currentUser = $rootScope.currentUser;
        $scope.toHome = function(){
            $location.path('/home');
        };
        $scope.toPinfo = function(){
            $location.path('/pinfo');
        };
        $scope.toMessages = function(){
            $location.path('/messages');
        };
        $scope.toPublications = function(){
            $location.path('/publications');
        };
        $scope.toDistrib = function(){
            $location.path('/distribMail');
        };
        $scope.logout = function() {
            AuthService.logout().success(function(){
                window.location.href = '../index.html';
            });
        }
    }
]);

