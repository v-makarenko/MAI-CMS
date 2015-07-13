/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('StatsController', ['$scope', '$rootScope',
    function ($scope, $rootScope) {
        $rootScope.$broadcast('main.tabSelected', 'stats');
    }
]);

