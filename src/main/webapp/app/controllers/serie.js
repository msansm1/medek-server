
angular.module('medekApp.controllers').controller('SerieController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'SerieService',
function($scope, $rootScope, $stateParams, $location, SerieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.serie = [ SerieService.serie($stateParams.serieId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("Serie : "+response.data);
		                              $scope.serie = response.data;
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.update = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);