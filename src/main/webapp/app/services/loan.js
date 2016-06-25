angular.module('medekApp.services').service('LoanService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.loans = function(id) {
        return $http.get('services/loans/loanto/'+id);
    };
    
    this.borrows = function(id) {
        return $http.get('services/loans/borrow/'+id);
    };
    
    this.loan = function(id) {
        return $http.get('services/loans/'+id);
    };
    
    this.createUpdate = function(loan) {
        return $http.post('services/loans', loan);
    };
} ]);