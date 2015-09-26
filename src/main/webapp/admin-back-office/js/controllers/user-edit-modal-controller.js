/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('UserEditModalController',
    function ($scope, $modalInstance, UsersService) {
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    });

