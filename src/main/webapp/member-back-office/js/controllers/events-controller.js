/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventsController', ['$scope', 'EventsService', '$modal',
    function ($scope, EventsService, $modal) {
        $scope.getAll = function () {
            EventsService.getAll().success(function (data) {
                if (data.status == "OK") {
                    $scope.events = data.data;
                }
            })
        };

        $scope.changePresence = function(presenceId, eventId){
            $scope.userEditInstance = $modal.open({
                templateUrl: '/common/html/modals/confirm-modal.html',
                controller: 'ConfirmModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {
                            methodOnSuccess: function () {
                            },
                            alertHead: 'Подтверждение участия',
                            alertText: presenceId ? 'Вы действительно будете участвовать?': 'Вы действительно не хотите участвовать?'
                        }
                    }
                }
            });
            $scope.userEditInstance.result.then(function () {
                EventsService.setPresence(presenceId, eventId).success(function(data){
                    if(data.status=='OK'){
                        $scope.getAll();
                    }
                });
            }, function () {
            });
        };

        $scope.presence = [{id: 1, name: 'Я участвую'}, {id:2, name: 'Я не участвую'}];

        $scope.getAll();

    }
]);

