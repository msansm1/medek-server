
angular.module('medekApp.controllers').controller('LoansController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'LoanService',
function($scope, $rootScope, $stateParams, $location, $modal, LoanService) {
    this.userLogin = $rootScope.user.login;
    $scope.filter = "";
    $scope.listLarge = true;
    
    $scope.getLoans = function() {
        $scope.listLarge = true;
		LoanService.loanslist(0, 50, 'id', 'asc').then(
    	           function(response) {
    	               console.log("Loans : "+response.data);
    	               $scope.loans = response.data;
    	           }, function(reason) {
    	               alert('FAILED !!!');
    	           });
    };
    
    $scope.loans = [ $scope.getLoans() ];
    
    $scope.openLoan = function(id) {
    };
    
    $scope.createLoan = function() {
    };
    
    $scope.searchLoans = function() {
        console.log('search criteria : '+$scope.filter);
    };

} ]);
