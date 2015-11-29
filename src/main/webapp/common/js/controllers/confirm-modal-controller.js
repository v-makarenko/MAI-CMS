/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('ConfirmModalController',
    function ($scope, $modalInstance, data) {
        $scope.alertHead = data.alertHead;
        $scope.alertText = data.alertText;

        $scope.ok = function(){
            $modalInstance.close(data.methodOnSuccess ? data.methodOnSuccess() : undefined);
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    });

