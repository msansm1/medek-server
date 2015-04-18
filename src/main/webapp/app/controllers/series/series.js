
angular.module('medekApp.controllers').controller('SeriesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'SerieService',
function($scope, $rootScope, $stateParams, $location, SerieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.series = [ SerieService.series()
                               .then(
		                          function(response) {
		                              console.log("Series : "+response.data);
		                              $scope.series = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.openSerie = function(id) {
    	$location.path('/series/'+id);
        $location.replace();
    }
    
    $scope.createSerie = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);