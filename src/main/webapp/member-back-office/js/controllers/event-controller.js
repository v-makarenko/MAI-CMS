/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventController', ['$scope', '$rootScope', '$routeParams', '$modal', 'EventsService', 'ThesisService',
    function ($scope, $rootScope, $routeParams, $modal, EventsService, ThesisService) {
        EventsService.get($routeParams.id).success(function (data) {
            if (data.status == 'OK') {
                $scope.currentEvent = data.data;
            }
        });

        $scope.refreshLists = function(){
            ThesisService.getConfirmed($routeParams.id, $rootScope.currentUser.id)
                .success(function (data) {
                    if (data.status == 'OK') {
                        $scope.confirmedList = data.data;
                    }
                });
            ThesisService.getWaitingForYourConfirmation($routeParams.id, $rootScope.currentUser.id).success(function (data) {
                if (data.status == 'OK') {
                    $scope.waitingForYourConfirmationList = data.data;
                }
            });
            ThesisService.getWaitingForCoauthorConfirmation($routeParams.id, $rootScope.currentUser.id).success(function (data) {
                if (data.status == 'OK') {
                    $scope.waitingForCoauthorConfirmationList = data.data;
                }
            });
        };

        $rootScope.$on('event.thesisesList.refresh', function () {
                $scope.refreshLists();
            }
        );

        $rootScope.$on('user.loaded', function () {
                $rootScope.$broadcast('event.thesisesList.refresh');
            }
        );

        $rootScope.$on('event.thesisesList.edit', function(event, data){
            $scope.thesisEditInstance = $modal.open({
                templateUrl: 'member-back-office/html/fragments/modal/thesis-edit-modal.html',
                controller: 'EditThesisModalController',
                //size: size,
                resolve: {
                    id: function () {
                        return data;
                    }

                }
            });
            $scope.thesisEditInstance.result.then(function () {
                $rootScope.$broadcast('event.thesisesList.refresh');
            }, function () {
            });
        });

        if($rootScope.currentUser && $rootScope.currentUser.id){
            $rootScope.$broadcast('event.thesisesList.refresh');
        }



        $scope.openEditThesisModal = function (index) {
            $rootScope.$emit('event.thesisesList.edit')
        }

    }
]);

