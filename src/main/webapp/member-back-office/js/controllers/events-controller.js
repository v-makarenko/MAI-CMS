/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventsController', ['$scope', '$rootScope', 'EventsService', '$modal',
    function ($scope, $rootScope, EventsService, $modal) {
        $scope.presence = [{id:true, name: 'Я участвую'}, {id:false, name: 'Я не участвую'}];

        $scope.getAll = function () {
            EventsService.getAll().success(function (data) {
                if (data.status == "OK") {
                    $scope.events = data.data;
                    _.forEach($scope.events, function(item){
                       item.present = _.find($scope.presence, {id: item.present});
                    });
                    var a = 0;
                }
            })
        };

        $scope.changePresence = function(eventId){
            var presenceId = _.find($scope.events, {id: eventId}).present.id;
            $scope.userEditInstance = $modal.open({
                templateUrl: 'common/html/modals/confirm-modal.html',
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
                EventsService.setPresence(presenceId, $rootScope.currentUser.id, eventId).success(function(data){
                    if(data.status=='OK'){
                        $scope.getAll();
                    }
                });

            }, function () {
            });
        };


        $scope.getAll();

    }
]);

