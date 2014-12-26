
angular.module('armApp.controllers').controller('IndicReportsController',[
'$scope',
'$rootScope',
'$modal',
'$stateParams',
'$translate',
'ProjectService',
function($scope, $rootScope, $modal, $stateParams, $translate, ProjectService) {
    this.userLogin = $rootScope.user.login;
    $scope.project = ProjectService.getProject($stateParams.projectId)
                        .then(
                                function(response) {
                                    console.log("project : "+response.data);
                                    $scope.project = response.data;
                                }, function(reason) {
                                    alert('FAILED !!!');
                                });

    $scope.gridindicators = { 
            enableSorting: true,
            enableFiltering: false,
            enableRowSelection: false,
            enableSelectAll: false,
            enableRowHeaderSelection: false,
            multiSelect: false,
            columnDefs: [ { field: 'name', displayName: 'name', headerCellFilter: 'translate'},
                          { field: 'description', displayName: 'description', headerCellFilter: 'translate'},
                          { field: 'result', displayName: 'result', headerCellFilter: 'translate'},
                          { field: 'target', displayName: 'target', headerCellFilter: 'translate'}],
            onRegisterApi: function( gridApi ) {
                $scope.gridApi = gridApi;
            }
        };

    $scope.gridindicators.data = [ ProjectService.projectIndics($stateParams.projectId)
                            .then(
                                    function(response) {
                                        console.log("Indics : "+response.data);
                                        $scope.gridindicators.data = response.data;
                                    }, function(reason) {
                                        alert('FAILED !!!');
                                    }) ];

    $scope.gridreports = { 
            enableSorting: true,
            enableFiltering: false,
            enableRowSelection: false,
            enableSelectAll: false,
            enableRowHeaderSelection: false,
            multiSelect: false,
            columnDefs: [ { field: 'name', displayName: 'name', headerCellFilter: 'translate'},
                          { field: 'description', displayName: 'description', headerCellFilter: 'translate'},
                          { field: 'categories', displayName: 'categories', headerCellFilter: 'translate'}],
            onRegisterApi: function( gridApi ) {
                $scope.gridApi = gridApi;
            }
        };

    $scope.gridreports.data = [ ProjectService.projectReports($stateParams.projectId)
                            .then(
                                    function(response) {
                                        console.log("Reports : "+response.data);
                                        $scope.gridreports.data = response.data;
                                    }, function(reason) {
                                        alert('FAILED !!!');
                                    }) ];
    
} ]);
