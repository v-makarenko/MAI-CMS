/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventEditController', ['$scope', '$routeParams', '$location', 'EventsService',
    function ($scope, $routeParams, $location, EventsService) {

        if ($routeParams.id === 'new') {
            $scope.currentEvent = {};
        } else {
            EventsService.get($routeParams.id).success(function (data) {
                if (data.status == 'OK') {
                    $scope.currentEvent = data.data;
                }
            })
        }


        $scope.save = function () {
            EventsService.save($scope.currentEvent).success(function (data) {
                if (data.status == 'OK') {
                    $location.path('/events');
                }
            });

        }


    }
]);

