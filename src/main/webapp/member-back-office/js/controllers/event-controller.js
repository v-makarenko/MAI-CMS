/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventController', ['$scope', '$routeParams', 'EventsService',
    function ($scope, $routeParams, EventsService) {
        EventsService.get($routeParams.id).success(function (data) {
            if (data.status == 'OK') {
                $scope.currentEvent = data.data;
            }
        });

    }
]);

