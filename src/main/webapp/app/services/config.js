angular.module('medekApp.services').service('ConfigService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.config = function() {
        return $http.get('services/admin/conf');
    };
    
} ]);