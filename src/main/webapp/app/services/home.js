angular.module('medekApp.services').service('HomeService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.mycollec = function() {
        return $http.get('services/home/mycollec');
    };

} ]);