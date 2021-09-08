package com.microservico.estoque.configuration.oauth;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    public @interface Category {

        @PreAuthorize("hasAuthority('CONSULT_CATEGORY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_CATEGORY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_CATEGORY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_CATEGORY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface City {

        @PreAuthorize("hasAuthority('CONSULT_CITY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_CITY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_CITY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_CITY')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface Input {

        @PreAuthorize("hasAuthority('CONSULT_INPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_INPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_INPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_INPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }

    }
    public @interface InputItem {

        @PreAuthorize("hasAuthority('CONSULT_INPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_INPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_INPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_INPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface Output {

        @PreAuthorize("hasAuthority('CONSULT_OUTPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_OUTPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_OUTPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_OUTPUT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface OutputItem {

        @PreAuthorize("hasAuthority('CONSULT_OUTPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_OUTPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_OUTPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_OUTPUT_ITEM')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface Product {

        @PreAuthorize("hasAuthority('CONSULT_PRODUCT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_PRODUCT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_PRODUCT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_PRODUCT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface Provider {

        @PreAuthorize("hasAuthority('CONSULT_PROVIDER')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_PROVIDER')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_PROVIDER')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_PROVIDER')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface Store {

        @PreAuthorize("hasAuthority('CONSULT_STORE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_STORE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_STORE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_STORE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

    public @interface Transport {
        @PreAuthorize("hasAuthority('CONSULT_TRANSPORT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('REGISTER_TRANSPORT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRegister { }

        @PreAuthorize("hasAuthority('REMOVE_TRANSPORT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanRemove { }

        @PreAuthorize("hasAuthority('EDIT_TRANSPORT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }
    }

}
