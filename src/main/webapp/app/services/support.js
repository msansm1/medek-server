angular.module('medekApp.services').service('SupportService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.supports = function() {
        return $http.get('services/supports');
    };
    
    this.support = function(id) {
        return $http.get('services/supports/'+id);
    };
    
    this.createUpdate = function(support) {
        return $http.post('services/supports', support);
    };
} ]);