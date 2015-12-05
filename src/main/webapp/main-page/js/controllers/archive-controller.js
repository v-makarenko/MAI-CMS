/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('ArchiveController', ['$scope','EventsService',
    function ($scope, EventsService) {
        $scope.loadEvents = function(){
            EventsService.getArchive().success(function(data){
                if(data.status == "OK"){
                    $scope.events = data.data;
                }
            })
        };

        $scope.loadEvents();

    }
]);

