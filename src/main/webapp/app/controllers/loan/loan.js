
angular.module('medekApp.controllers').controller('LoanController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'LoanService',
function($scope, $rootScope, $stateParams, $location, $modal, LoanService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.loanId) != 'undefined') {
	    $scope.loan = [ LoanService.loan($stateParams.loanId)
	                               .then(
			                          function(response) {
			                        	  $('.mainlist').addClass('col-md-4');
			                        	  $('.mainlist').removeClass('col-md-8');
			                              console.log("Loan : "+response.data);
			                              $scope.loan = response.data;
			                              $('.itempanel').addClass('col-md-6');
			                          }, function(reason) {
			                              alert('FAILED !!!');
			                          }) ];
    } else {
    	$scope.loan = { 
    	};
    	$('.mainlist').addClass('col-md-4');
    	$('.mainlist').removeClass('col-md-8');
    	$('.itempanel').addClass('col-md-6');
	}

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
    
} ]);
