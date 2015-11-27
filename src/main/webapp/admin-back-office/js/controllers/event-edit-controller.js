/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventEditController', ['$scope', '$routeParams', '$location', 'EventsService', 'SectionsService',
    function ($scope, $routeParams, $location, EventsService, SectionsService) {
        $scope.eventId = $routeParams.id;

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
                    if ($routeParams.id === 'new') {
                        $location.path('/events/' + data.data.id);
                    } else {
                        $scope.loadEvent();
                    }
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

        $scope.addSection = function () {
            var section = {};
            section.eventId = $routeParams.id;
            SectionsService.save(section).success(function (data) {
                if (data.status === 'OK') {
                    $scope.loadEvent();
                }
            });
        };

        $scope.finishSectionsEditing = function () {
            $scope.sectionClick($scope.currentEvent.sectionList, _.find($scope.currentEvent.sectionList, {editing: true}));
            _.each($scope.currentEvent.sectionList, function (item) {
                item.editing = false
            });
        };

        $scope.sectionClick = function (list, item) {
            if(item == null) return;
            _.each(list, function (listItem) {
                if (listItem.editing) {
                    var saveItem = angular.copy(listItem);
                    saveItem.editing = undefined;
                    SectionsService.save(saveItem);
                }
                listItem.editing = listItem.id == item.id;
            })
        };

        $scope.deleteSection = function (id) {
            SectionsService.delete(id).success(function (data) {
                if (data.status == 'OK') {
                    alert('Удалено');
                    $scope.loadEvent();
                }
            });
        };


        $scope.currentTechPeopleItem = {};

        $scope.loadEvent();

    }
]);

