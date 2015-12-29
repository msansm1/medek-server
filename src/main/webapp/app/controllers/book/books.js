
angular.module('medekApp.controllers').controller('BooksController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'BookService',
function($scope, $rootScope, $stateParams, $location, $modal, BookService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getBooks = function() {
    	if ($location.path() === '/books/me') {
        	BookService.userBooks($rootScope.user.id).then(
	           function(response) {
	               console.log("Books : "+response.data);
	               $scope.books = response.data;
	           }, function(reason) {
	               alert('FAILED !!!');
	           });
    	} else {
        	BookService.books().then(
	           function(response) {
	               console.log("Books : "+response.data);
	               $scope.books = response.data;
	           }, function(reason) {
	               alert('FAILED !!!');
	           });
    	}
    };
    
    $scope.books = [ $scope.getBooks() ];
    
    $scope.openBook = function(id) {
    	var index = 0;
    	if ($location.path().indexOf('/me/') > -1) {
     	   index = 9;
        } else {
     	   index = 10;
        }
    	$location.path($location.path().substring(0,index)+'/view/'+id);
        $location.replace();
    };
    
    $scope.createBook = function(book) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/book/addbook.html',
            controller: 'AddBookController',
            scope: $scope
          });
    };

} ]);

angular.module('medekApp.controllers').controller('AddBookController',[
'$scope',
'$rootScope',
'$modalInstance',
'BookService',
'BookTypeService',
'CollectionService',
'EditorService',
'LangService',
'StoryGenreService',
function ($scope, $rootScope, $modalInstance, BookService, BookTypeService, CollectionService, EditorService, LangService, StoryGenreService) {
	$scope.book = { id: null,
			title: '',
			author: '',
			authorId: null,
			editor: '',
			editorId: null,
			collection: '',
			collectionId: null,
			cover: '',
			description: '',
			publicationDate: null,
			genre: '',
			genreId: null,
			type: '',
			typeId: null,
			lang: '',
			langId: null,
			series: '',
			bookNb: null,
			isSerieDone: false,
	};
    
    $scope.types = [ BookTypeService.booktypes()
                        .then(
	                          function(response) {
	                              console.log("Book types : "+response.data);
	                              $scope.types = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.collections = [ CollectionService.collections()
                        .then(
	                          function(response) {
	                              console.log("Collections : "+response.data);
	                              $scope.collections = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.editors = [ EditorService.editors()
                        .then(
	                          function(response) {
	                              console.log("Editors : "+response.data);
	                              $scope.editors = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.langs = [ LangService.langs()
                        .then(
	                          function(response) {
	                              console.log("Langs : "+response.data);
	                              $scope.langs = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.genres = [ StoryGenreService.storygenres()
                        .then(
	                          function(response) {
	                              console.log("Story genres : "+response.data);
	                              $scope.genres = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

    $scope.authors = [ BookService.artists()
                               .then(
		                          function(response) {
		                              console.log("authors : "+response.data);
		                              $scope.authors = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];

    // for date picker
    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();
      $scope.opened = true;
    };
    
    $scope.saveBook = function() {
    	BookService.saveBook($scope.book).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Book saved !'});
                	$scope.getBooks();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save book failed'});
                });
    };

    $scope.ok = function () {
    	$scope.saveBook();
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);