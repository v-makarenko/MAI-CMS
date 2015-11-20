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

        $rootScope.$on('user.loaded', function () {
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
        });

        $scope.openEditThesisModal = function (index) {
            $scope.thesisEditInstance = $modal.open({
                templateUrl: 'member-back-office/html/fragments/modal/thesis-edit-modal.html',
                controller: 'EditThesisModalController',
                //size: size,
                resolve: {
                    id: function () {
                        return $routeParams.id;
                    }

                }
            });
        }

    }
]);

