/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MainPageController', ['$scope', '$modal','EventsService',
    function ($scope, $modal, EventsService) {
        $scope.openLoginModal = function(){
            $scope.loginModal = $modal.open({
                templateUrl: 'main-page/html/modals/login-modal.html',
                controller: 'LoginModalController'
            });
        };
        $scope.loadAll = function(){
            // тут надо загрузить Фотоархив и нашу команду
            // TODO fix only archived photos
            EventsService.getAllPublic().success(function(data){
                if(data.status == 'OK'){
                    $scope.archiveEvents = data.data;
                }
            });
        };
        //$scope.loadAll();

    }
]);

