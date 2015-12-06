/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('LogsController',
    function ($scope, $modal, LogService) {
        LogService.getAll().success(function(data){
           if(data.status == 'OK'){
               $scope.logs = data.data;
           }
        });
    });

