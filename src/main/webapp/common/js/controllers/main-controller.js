/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MainController', ['$scope','$location',
    function ($scope, $location) {
        $scope.toHome = function(){
            $location.path('/home')
        };
        $scope.toPinfo = function(){
            $location.path('/pinfo')
        };
        $scope.toMessages = function(){
            $location.path('/messages')
        };
        $scope.toPublications = function(){
            $location.path('/publications')
        }
    }
]);

