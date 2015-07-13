/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MenuController', ['$scope', '$rootScope', '$location',
    function ($scope, $rootScope, $location) {
        $rootScope.$on('main.tabSelected', function(event, data){
            $scope.menuItemSelected = data;
        });
        // consts for tabs
        var TAB_ORDERS_ID = 'orders';
        var TAB_MENU_ID = 'menu';
        var TAB_STATS_ID = 'stats';

        // default is orders page
        $scope.menuItemSelected = TAB_ORDERS_ID;


        $scope.location = $location;

        $scope.gotoLink = function(link){
            $location.path(link)
        };


        $scope.logout = function(){
            $rootScope.$broadcast('events.exit');
        };

        $scope.sendReport = function(){
            $rootScope.$broadcast('events.sendReport');
        };
    }]);

