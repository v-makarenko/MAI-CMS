/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('WPController',
    function ($scope, $rootScope, $modal, WPService) {
        $scope.users = [];

        $scope.getAll = function () {
            WPService.getAll().success(function (data) {
                $scope.wplaces = data.data;
            });
        };

        $scope.editWP = function (id) {
            $scope.userEditInstance = $modal.open({
                templateUrl: 'admin-back-office/html/modals/edit-wp-modal.html',
                controller: 'WPEditModalController',
                //size: size,
                resolve: {
                    id: function () {
                        return id;
                    }
                }
            });
            $scope.userEditInstance.result.then(function () {
                $scope.getAll();
            }, function () {
            });

        };

        $scope.delete = function (id) {
            $scope.wplaceDeleteInstance = $modal.open({
                templateUrl: 'common/html/modals/confirm-modal.html',
                controller: 'ConfirmModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {
                            alertHead: 'Удаление органищации',
                            alertText: 'Удалить организацию?'
                        }
                    }
                }
            });
            $scope.wplaceDeleteInstance.result.then(function () {
                WPService.delete(id).success(function (data) {
                    if (data.status == 'OK') {
                        $scope.getAll();
                    }
                });
            }, function () {
            });
        };

        $scope.connect = function (id) {
            $scope.wplaceConnectInstance = $modal.open({
                templateUrl: 'admin-back-office/html/modals/connect-wp-modal.html',
                controller: 'WorkingPlaceConnectModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {
                            id: id
                        }
                    }
                }
            });
        };

        $rootScope.$on('wplaces.reload', $scope.getAll);
        $scope.getAll();

    });

