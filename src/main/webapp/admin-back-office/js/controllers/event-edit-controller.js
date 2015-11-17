/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventEditController', ['$scope', '$routeParams', '$location', 'EventsService',
    function ($scope, $routeParams, $location, EventsService) {
        $scope.loadEvent = function () {
            if ($routeParams.id === 'new') {
                $scope.currentEvent = {};
            } else {
                EventsService.get($routeParams.id).success(function (data) {
                    if (data.status == 'OK') {
                        $scope.currentEvent = data.data;
                    }
                })
            }
        };

        $scope.save = function () {
            _.each(['techPeopleList'], function (listName) {
                _.each($scope.currentEvent[listName], function (item) {
                    if (!/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/.test(item.id)) {
                        item.id = null;
                    }
                })
            });

            EventsService.save($scope.currentEvent).success(function (data) {
                if (data.status == 'OK') {
                    alert('Сохранено');
                    $scope.loadEvent();
                }
            });

        };

        $scope.saveItem = function (list, itemName) {
            var item = $scope[itemName];
            if (item.id == null) {
                item.id = list.length;
                list.push(item);
            } else {
                list[_.indexOf(list, {id: item.id})] = item;
            }
            $scope[itemName] = {};

        };

        $scope.currentTechPeopleItem = {};

        $scope.loadEvent();
    }
]);

