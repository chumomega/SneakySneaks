import { wrap } from 'rest';
import defaultRequest from 'rest/interceptor/defaultRequest';
import mime from 'rest/interceptor/mime';
import uriTemplateInterceptor from './uriTemplateInterceptor';
import errorCode from 'rest/interceptor/errorCode';
import { child } from 'rest/mime/registry';

const registry = child();

registry.register('text/uri-list', require('./uriListConverter'));
registry.register('application/hal+json', require('rest/mime/type/application/hal'));

export default wrap(mime, { registry: registry })
		.wrap(uriTemplateInterceptor)
		.wrap(errorCode)
        .wrap(defaultRequest, { headers: { 'Accept': 'application/hal+json' }});
        
        