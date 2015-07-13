/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('SettingsController',
    function ($scope, $rootScope) {
        $rootScope.$on('event.userLoaded', function(){
            $scope.rootScope = $rootScope.currentUser;
        });

        $scope.save = function(){

        }

    });

