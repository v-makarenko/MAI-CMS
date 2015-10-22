/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('UsersController',
    function ($scope, $modal, UsersService) {
        $scope.users = [];

        $scope.getAll = function(){
            UsersService.getAllUsers().success(function(data){
                $scope.users = data.data;
            });
        };

        $scope.editUser = function(id) {
            $scope.userEditInstance = $modal.open({
                templateUrl: 'admin-back-office/html/modals/edit-user-modal.html',
                controller: 'UserEditModalController'
                //size: size,
                //resolve: {
                //    items: function () {
                //        return $scope.items;
                //    }
                //}
            });
        };



        $scope.getAll();

    });

