/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventEditController', ['$scope', '$routeParams', '$location', '$modal', 'EventsService', 'SectionsService', 'WPService', 'FinancialDocumentTypeService',
    function ($scope, $routeParams, $location, $modal, EventsService, SectionsService, WPService, FinancialDocumentTypeService) {
        $scope.eventId = $routeParams.id;
        $scope.organisationList = [{id: 1, name: 'fdsfds'}];
        $scope.data = {};


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
            $scope.itemClick($scope.currentEvent.sectionList, _.find($scope.currentEvent.sectionList, {editing: true}), SectionsService);
            _.each($scope.currentEvent.sectionList, function (item) {
                item.editing = false
            });
        };

        $scope.editFinDocType = function (id) {
            $scope.editFinDocInstance = $modal.open({
                templateUrl: 'admin-back-office/html/modals/edit-fin-doc-type-modal.html',
                controller: 'FinDocTypeModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {
                            id: id,
                            eventId: $scope.currentEvent.id
                        }
                    }

                }
            });
            $scope.editFinDocInstance.result.then(function () {
                $scope.loadEvent();
            }, function () {
            });
        };

        $scope.itemClick = function (list, item, service) {
            if (item == null) return;
            _.each(list, function (listItem) {
                if (listItem.editing) {
                    var saveItem = angular.copy(listItem);
                    saveItem.editing = undefined;
                    service.save(saveItem);
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

        $scope.deleteFinDoc = function (id) {
            FinancialDocumentTypeService.delete(id).success(function (data) {
                alert('Удалено');
                $scope.loadEvent();

            });
        };

        $scope.addOrgToList = function () {
            if (!$scope.currentEvent.orgOrganisationList) {
                $scope.currentEvent.orgOrganisationList = [];
            }
            var duplicates = _.filter($scope.currentEvent.orgOrganisationList, {id: $scope.data.currentOrgOrganisationId});
            if (!duplicates || duplicates.length != 0) {
                return;
            }
            $scope.currentEvent.orgOrganisationList.push(_.filter($scope.organisationList, {id: $scope.data.currentOrgOrganisationId})[0])
        };


        WPService.getAllPublic().success(function (data) {
            if (data.status == 'OK') {
                $scope.organisationList = data.data;
            }
        });


        $scope.currentTechPeopleItem = {};
        $scope.currentFinancialDocument = {};

        $scope.loadEvent();


    }
]);

