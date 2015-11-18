/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('SectionsController',
    function ($scope, $modal, SectionsService) {
        $scope.users = [];

        $scope.getAll = function(){
            SectionsService.getAll().success(function(data){
                $scope.sections = data.data;
            });
        };

        $scope.getAll();

    });

