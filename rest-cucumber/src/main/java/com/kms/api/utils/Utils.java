package com.kms.api.utils;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

public class Utils {
  /**
   * * Generate Pojo object from json file
   *
   * @param inputJsonUrl
   * @param outputJavaClassDirectory
   * @param packageName
   * @param javaClassName
   * @throws IOException
   */
  public static void convertJsonToJavaClass(
      URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName)
      throws IOException {
    JCodeModel jcodeModel = new JCodeModel();

    GenerationConfig config =
        new DefaultGenerationConfig() {
          @Override
          public boolean isGenerateBuilders() {
            return true;
          }

          @Override
          public SourceType getSourceType() {
            return SourceType.JSON;
          }
        };

    SchemaMapper mapper =
        new SchemaMapper(
            new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()),
            new SchemaGenerator());
    mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

    jcodeModel.build(outputJavaClassDirectory);
  }
}
