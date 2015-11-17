/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('WPController',
    function ($scope, $modal, WPService) {
        $scope.users = [];

        $scope.getAll = function(){
            WPService.getAll().success(function(data){
                $scope.wplaces = data.data;
            });
        };

        $scope.editWP = function(id) {
            $scope.userEditInstance = $modal.open({
                templateUrl: 'admin-back-office/html/modals/edit-wp-modal.html',
                controller: 'WPEditModalController',
                //size: size,
                resolve: {
                    id: function() {return id;}
                }
            });
            $scope.userEditInstance.result.then(function () {
                $scope.getAll();
            }, function () {
            });

        };



        $scope.getAll();

    });

