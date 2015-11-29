/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('WorkingPlaceConnectModalController',
    function ($scope, $rootScope, $modalInstance, WPService, data) {
        $scope.originalWPId = data.id;
        $scope.data = {newOriginalWPIndex: 0};


        $scope.wpReduceList = [{id: data.id}, {}]; // two to connect for example

        WPService.find($scope.originalWPId).success(function (data) {
            if (data.status == 'OK') {
                $scope.originalWP = data.data;
            }
        });

        WPService.getAll().success(function (data) {
            if (data.status == 'OK') {
                $scope.wpList = data.data;
            }
        });

        $scope.ok = function () {
            WPService.connect($scope.wpReduceList, $scope.wpReduceList[$scope.data.newOriginalWPIndex].id).success(function (data) {
                if (data.status == 'OK') {
                    $rootScope.$broadcast('wplaces.reload')
                    $modalInstance.dismiss('cancel');
                }
            })
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    });

