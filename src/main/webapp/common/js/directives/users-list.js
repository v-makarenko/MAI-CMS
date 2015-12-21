/**
 * Created by VMakarenko on 18.11.2015.
 */
angular.module("app").directive('usersList', ['$rootScope', 'UsersService',
    function ($rootScope, UsersService) {
    return {
        templateUrl: 'common/html/fragments/directives/users-list.html',
        link: function (scope, element, attrs) {
            scope.filter = {
                eventId : scope.eventId
            };

            scope.getFiltered = function(){
                UsersService.getFiltered(scope.filter)
                    .success(function(data){
                        if(data.status=='OK'){
                            scope.list = data.data;
                        }
                    });
            };
            
            scope.getFiltered();
        },
        scope: {
            list: '=list',
            eventId: '=eventId',
            userLink: '=userLink'
        }
    }
}]);
