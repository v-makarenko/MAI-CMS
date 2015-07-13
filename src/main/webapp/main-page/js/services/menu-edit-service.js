angular.module("app").service('MenuEditService',
    function ($http, $q) {
        var url = '/api/private/menuEdit';
        this.getAll = function (filter) {
            return $http.post(url+'/getAll',{});
        };
        this.insert = function (position) {
            return $http.post(url,position);
        };
        this.update = function (position) {
            return $http.put(url,position);
        };
        this.delete = function (id) {
            return $http.delete(url + '?id='+id);
        };
        this.setNewPosition = function (id, afterId) {
            var deferred = $q.defer();
            deferred.resolve({
                result: 'OK',
                msg: undefined
            });
            return deferred.promise;
        };

        this.getAllWeekDays = function(){
            return $http.get(url + '/allDaysOfWeek');
        }
    }
);