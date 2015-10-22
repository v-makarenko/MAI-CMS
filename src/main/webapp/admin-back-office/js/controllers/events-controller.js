/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventsController', ['$scope', '$modal', 'EventsService',
    function ($scope, $modal, EventsService) {
        $scope.getAll = function () {
            EventsService.getAll().success(function (data) {
                if (data.status == "OK") {
                    $scope.events = data.data;
                }
            })
        };

        $scope.delete = function (index) {
            $scope.userEditInstance = $modal.open({
                templateUrl: 'common/html/modals/confirm-modal.html',
                controller: 'ConfirmModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {
                            methodOnSuccess: function () {
                                EventsService.delete($scope.events[index].id);

                            },
                            alertHead: 'Подтверждение',
                            alertText: 'Вы хотите удалить событие?'
                        }
                    }

                }
            });
            $scope.userEditInstance.result.then(function () {
                $scope.getAll();
            }, function () {
            });

        };

        $scope.getAll();

    }
]);

